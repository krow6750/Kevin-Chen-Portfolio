import numpy as np
import matplotlib.pyplot as plt

np.random.seed(42)

n_trials = 10000
n_questions = 12
total_questions = 120
class_I_questions = 90
class_II_questions = total_questions - class_I_questions
passing_score = 36

class_I_probs = [0.6, 0.3, 0.1, 0.0, 0.0]
class_II_probs = [0.0, 0.1, 0.4, 0.4, 0.1]
grades = [4, 3, 2, 1, 0]

# Generate a list of question indices, with the first 90 being class I and the rest being class II
questions = list(range(total_questions))
np.random.shuffle(questions)

# Monte Carlo simulation
scores = []
for _ in range(n_trials):
    selected_questions = np.random.choice(questions, n_questions, replace=False)
    test_score = 0
    for q in selected_questions:
        if q < class_I_questions:
            grade = np.random.choice(grades, p=class_I_probs)
        else:
            grade = np.random.choice(grades, p=class_II_probs)
        test_score += grade
    scores.append(test_score)

# Generate histogram
bins = np.arange(0, n_questions * 4 + 1, 1)
plt.hist(scores, bins=bins, density=True)
plt.xlabel('Score')
plt.ylabel('Probability')
plt.title('Histogram of Scores')
plt.show()

# Calculate probability of passing the test
pass_count = sum(score >= passing_score for score in scores)
pass_probability = pass_count / n_trials
print(f'Probability of passing the test: {pass_probability:.4f}')

# Calculate uncertainty (95% confidence interval)
z_score = 1.96  # For a 95% confidence interval
sample_std_dev = np.std(scores)
margin_of_error = z_score * (sample_std_dev / np.sqrt(n_trials))
print(f'Uncertainty: ±{margin_of_error:.4f}')
