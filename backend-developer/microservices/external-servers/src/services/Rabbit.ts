import amqplib from "amqplib";
import { genDescription } from "../utils/generate";
import { IResponse } from "../types/response";

interface IRequest {
  cpf: String;
}

const brokerAddress = process.env.BROKER_URL;

(async () => {
  try {
    await new Promise((promise) => setTimeout(promise, 4000));
    const upReqName = "update-request";
    const upResName = "update-response";

    console.log(`trying to connect to broker.`);
    const conn = await amqplib.connect("amqp://guest:guest@rabbitmq:5672");
    console.log("connection aquired");

    const qUpRequest = await conn.createChannel();
    const qUpResponse = await conn.createChannel();
    await qUpRequest.assertQueue(upReqName);

    qUpRequest.consume(upReqName, (msg) => {
      if (msg) {
        const parsedMsgContent: IRequest = JSON.parse(msg.content.toString());
        const description: IResponse = genDescription(parsedMsgContent.cpf);

        qUpRequest.ack(msg);

        setTimeout(() => {
          qUpResponse.sendToQueue(
            upResName,
            Buffer.from(JSON.stringify(description))
          );
        }, Math.random() * 1000);
      }
    });
  } catch (error) {
    if (error instanceof Error) console.log(error.stack);
  }
})();
