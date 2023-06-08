import express from 'express';
import { getAllFoods, createFood, deleteFood, updateFood, getFoodDetail } from '../handlers/foodsHandlers.js';

const router = express.Router();

// all routes here are starting with /foods
router.get('/', getAllFoods);
router.post('/', createFood);
router.get('/:id', getFoodDetail);
router.delete('/:id', deleteFood);
router.put('/:id', updateFood);

export default router;