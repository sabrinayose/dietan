import express from 'express';
import { 
    getAllUserMedicalHistories, 
    createUserMedicalHistory, 
    updateUserMedicalHistory 
} from '../handlers/user_medical_historiesHandlers.js';

const router = express.Router();

// all routes here are starting with /userMedicalHistories
router.get('/', getAllUserMedicalHistories);
router.post('/', createUserMedicalHistory);
router.put('/:id', updateUserMedicalHistory);

export default router;