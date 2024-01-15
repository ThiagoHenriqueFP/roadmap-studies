import amqplib from "amqplib";

(async () => {
  try {
    const upReqName = "update-request";
    const upResName = "update-response";
    const conn = await amqplib.connect("amqp://localhost");

    const qUpRequest = await conn.createChannel();
    await qUpRequest.assertQueue(upReqName);

    qUpRequest.consume(upReqName, (msg) => {
      if (msg) {
        console.log(msg.content.toString());
        qUpRequest.ack(msg);
      }
    });

    const qUpResponse = await conn.createChannel();

    setInterval(() => {
      qUpResponse.sendToQueue(upResName, Buffer.from("iaee"));
    }, 1000);
  } catch (error) {
    if (error instanceof Error) console.log(error.message);
  }
})();
