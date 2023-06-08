import express from 'express';
import { getAllUser_wh, createUser_wh, updateUser_wh } from '../handlers/user_whsHandlers.js';


const router = express.Router();


// all routes here are starting with /use_whs

router.get('/', getAllUser_wh);
router.post('/', createUser_wh);
router.put('/:id', updateUser_wh);


export default router;