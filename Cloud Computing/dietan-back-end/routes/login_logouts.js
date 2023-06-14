import express from 'express';
import {signupUser, loginUser, logoutUser, } from '../handlers/login_logoutHandlers.js';

const router = express.Router();

// all routes here are starting with /login_logout
router.get('/', loginUser);
router.post('/', signupUser);
router.get('/logout', logoutUser);

export default router;