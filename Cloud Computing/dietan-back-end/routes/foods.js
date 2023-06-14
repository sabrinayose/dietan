import express from 'express';
import { getAllFoods } from '../handlers/foodsHandlers.js';

const router = express.Router();

// all routes here are starting with /foods
router.get('/', getAllFoods);

export default router;