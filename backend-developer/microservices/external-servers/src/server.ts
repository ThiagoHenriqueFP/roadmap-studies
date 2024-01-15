import express from "express";
import { routes } from "./routes";

import "./services/Rabbit";

const app = express();

app.use("/", routes);

app.listen(3000, () => console.log("server running..."));
