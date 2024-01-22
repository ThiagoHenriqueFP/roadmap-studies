import amqplib from "amqplib";
import { genDescription } from "../utils/generate";
import { IResponse } from "../types/response";

interface IRequest {
  cpf: String;
}

(async () => {
  try {
    const upReqName = "update-request";
    const upResName = "update-response";
    const conn = await amqplib.connect("amqp://localhost");

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

    // setInterval(() => {
    //   qUpResponse.sendToQueue(upResName, Buffer.from("iaee"));
    // }, 1000);
  } catch (error) {
    if (error instanceof Error) console.log(error.message);
  }
})();
