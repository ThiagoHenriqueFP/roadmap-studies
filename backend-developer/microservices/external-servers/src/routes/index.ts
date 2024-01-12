import { Router } from 'express';
import { actuatorRoutes } from './actuator.route';

const routes = Router();

routes.use("/", actuatorRoutes);

export {routes}