import express from 'express';
import { getAllUsers, createUser } from '../handlers/userHandlers.js';


const router = express.Router();


// all routes here are starting with /user
router.get('/', getAllUsers);
router.post('/', createUser);


export default router;