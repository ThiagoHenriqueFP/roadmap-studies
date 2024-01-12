import { Router } from 'express';

const router = Router();

router.get("/actuator/health", (req, res) => {
  return {
    status: "UP"
  }
});

export const actuatorRoutes = router;