#part 1 write a function to simulate...

rollDie <- function() {
  die1 <- sample(1:6, 1)
  die2 <- sample(1:6, 1)
  die3 <- sample(1:6, 1)
  return(sum(die1 + die2 + die3))
}

#part 2 

prob <- function(num_rolls = 10000) {
  # this function returns the estimate of a sum of 10 when rolling three fair dice
  count <- 0
  for (i in 1:num_rolls) {
    if (rollDie() == 10) { #if sum is = 10 then it's added to the total count
      count <- count + 1
    }
  }
  return(count / num_rolls)
}

#part 3

probabilityEstimates <- function(num_estimates = 100, num_rolls = 1000)
{
  # this function creates a vector of size num_estimates and fills
  # that vector with estimates of rolling a sum of seven using
  # num_rolls rolls per computed estimate
  estimates <- rep(0, num_estimates)
  for (i in 1:num_estimates) {
    estimates[i] <- probabilityEstimates(num_rolls)
  }
  return(estimates)
}

#part 4



EstimateHistogram <- function(num_estimates = 100, num_rolls = 1000, title)
{
  all_estimates <- probabilityEstimates()
  hist(all_estimates, xlim = c(0.05, 0.2), main = title)
  m <- mean(all_estimates)
  abline(v = m, col = "blue", lwd = 2)
  s <- sd(all_estimates)
  abline(v = c(m - 2*s, m + 2*s), col = "red", lwd = 2, lty = "dashed") 
  print(quantile(all_estimates, prob = c(0.5, 0.75, 0.95, 0.99)))
}

#part 5

#par(mfrow = c(1,3)) # display 1 row, 3 columns
par(mfrow = c(3,1)) # display 3 rows, 1 column

EstimateHistogram(num_rolls = 10, title = "Histogram of 1000 Estimates of 10 rolls of Three Pair Dice")
EstimateHistogram(num_rolls = 100, title = "Histogram of 1000 Estimates of 100 rolls of Three Pair Dice")
EstimateHistogram(title = "Histogram of 1000 Estimates of 1000 rolls of Three Pair Dice")

#part 6

EstimateHistogram(num_rolls = 10, title = "Histogram of 10 Estimates of 1000 rolls of Three Pair Dice")
EstimateHistogram(num_rolls = 100, title = "Histogram of 100 Estimates of 1000 rolls of Three Pair Dice")
EstimateHistogram(title = "Histogram of 1000 Estimates of 1000 rolls of Three Pair Dice")

















