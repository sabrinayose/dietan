import express from 'express';
import bodyParser from 'body-parser';
import foodsRoutes from './routes/foods.js';
import user_daily_mealRoutes from './routes/user_daily_meals.js';
import user_whsRoutes from './routes/user_whs.js';
import userRoutes from './routes/users.js';
import login_logout from './routes/login_logouts.js'
import user_activityRoutes from './routes/user_activity.js';
import functionRoutes from './routes/function.js';

const app = express();
const PORT = 5000;

app.use(bodyParser.json());

app.use('/foods', foodsRoutes);
app.use('/users', userRoutes);
app.use('/user_daily_meals', user_daily_mealRoutes);
app.use('/user_whs', user_whsRoutes);
app.use('/login_logout', login_logout);
app.use('/user_activities', user_activityRoutes);
app.use('/function', functionRoutes)

app.get('/', (req, res) => {
    console.log('[TEST]!');

    res.send("Hello from Homepage");
} );


app.listen(PORT, () => console.log(`Server running on port : http://localhost:${PORT}`));