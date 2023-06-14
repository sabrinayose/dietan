import express from 'express';
import { 
    getAllUserActivities, 
    createUserActivity, 
} from '../handlers/user_activityHandlers.js';

const router = express.Router();

// all routes here are starting with /user_activities
router.get('/', getAllUserActivities);
router.post('/', createUserActivity);

export default router;