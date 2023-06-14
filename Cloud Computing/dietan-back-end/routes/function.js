import express from 'express';
import { getBmi, getRecommendation, getUserInputCalories } from '../handlers/functionHandlers.js';


const router = express.Router();


// all routes here are starting with /user
router.get('/bmi/:id', getBmi);
router.get('/', getRecommendation);
router.get('/user_input/:id', getUserInputCalories);


export default router;