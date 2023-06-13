# Machine Learning Model Documentation

## Dataset Source

We built this model from this Kaggle dataset: [Diet Plan Recommendation](https://www.kaggle.com/datasets/vechoo/diet-plan-recommendation)

After we ganthered the dataset, we splited it as following ratio:
- 95% training data
- 5% validation/test data

## Training Model Method

We trained this model using Deep Neural Networks with Log-Cosh as a loss function and Adam as an optimizer.

## Note on the Model Usage

This Tensorflow Lite model requires interpreter for the usage. This model use the following inputs:
- activity_level (float)
- age (float)
- gender ('M' or 'F')
- bmi (float)
