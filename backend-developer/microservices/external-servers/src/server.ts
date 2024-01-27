import express from "express";
import { routes } from "./routes";

import "./services/Rabbit";

const app = express();

app.use("/", routes);

app.listen(process.env.PORT, () => console.log("server running..."));
