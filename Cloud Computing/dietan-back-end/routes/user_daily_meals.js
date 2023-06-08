import express from 'express';
import { createUser_daily_meal } from '../handlers/user_daily_mealsHandlers.js';


const router = express.Router();


// all routes here are starting with /user_daily_meals

router.post('/', createUser_daily_meal);


export default router;