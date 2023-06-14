import express from 'express';
import bodyParser from 'body-parser';
import foodsRoutes from './routes/foods.js';
import user_daily_mealRoutes from './routes/user_daily_meals.js';
import user_whsRoutes from './routes/user_whs.js';
import userRoutes from './routes/users.js';
import user_medical_historyRoutes from './routes/user_medical_histories.js';
import login_logout from './routes/login_logouts.js'

const app = express();
const PORT = 5000;

app.use(bodyParser.json());

app.use('/foods', foodsRoutes);
app.use('/users', userRoutes);
app.use('/user_daily_meals', user_daily_mealRoutes);
app.use('/user_whs', user_whsRoutes);
app.use('/user_medical_histories', user_medical_historyRoutes);
app.use('/login_logout', login_logout);


app.get('/', (req, res) => {
    console.log('[TEST]!');

    res.send("Hello from Homepage");
} );


app.listen(PORT, () => console.log(`Server running on port : http://localhost:${PORT}`));