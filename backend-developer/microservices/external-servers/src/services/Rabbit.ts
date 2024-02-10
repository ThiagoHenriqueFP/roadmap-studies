import amqplib from "amqplib";
import { genDescription } from "../utils/generate";
import { IResponse } from "../types/response";
import dotenv from "dotenv";
dotenv.config();

interface IRequest {
  cpf: String;
}

const brokerAddress = process.env.BROKER_URL || "amqp://localhost";

(async () => {
  try {
    const upReqName = "update-request";
    const upResName = "update-response";

    let i = 1;

    let conn;
    while (conn == null) {
      console.log(`trying to connect to broker. Attempt ${i}`);
      i++;

      await new Promise((promise) => setTimeout(promise, 4000));

      try {
        conn = await amqplib.connect(brokerAddress);
      } catch (err) {
        conn = undefined;
        if (i > 10) {
          throw err;
        }
      }
    }
    console.log("connection acquired with " + brokerAddress);

    const qUpRequest = await conn.createChannel();
    const qUpResponse = await conn.createChannel();
    await qUpRequest.assertQueue(upReqName);

    qUpRequest.consume(upReqName, (msg) => {
      if (msg) {
        qUpRequest.ack(msg);
        try {
          console.log(msg.content.toString());
          const parsedMsgContent: IRequest = JSON.parse(msg.content.toString());
          const description: IResponse = genDescription(parsedMsgContent.cpf);
          console.log(description)

          // get random seconds to delay the response
          const time = Math.random() * 100000;
          setTimeout(() => {
            qUpResponse.sendToQueue(
                upResName,
                Buffer.from(JSON.stringify(description))
            );
          }, time);
        } catch (error) {
          if (error instanceof Error)
            console.log("discarding message because: " + error.message);
        }
      }
    });
  } catch (error) {
    if (error instanceof Error) console.log(error.stack);
  }
})();
