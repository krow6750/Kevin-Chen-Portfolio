#Name: Kevin Chen
#Assignment: HW1, dice rolls
#Course: DCS 307
#Instructor: Barry Lawson

#1:

rollDie <- function()
{
  die1 <- sample(1:6, 1)
  die2 <- sample(1:6, 1)
  die3 <- sample(1:6, 1)
  return(sum(die1 + die2 + die3))
}


#2:

prob <- function(num_rolls = 1000)
{
  count <- 0
  for (i in 1:num_rolls) {
    if (rollDie() == 10) {
      count <- count + 1
    }
  }
  return(count / num_rolls)
}


#3:

probEstimates <- function(num_estimates = 100, num_rolls = 1000)
{
  estimates <- rep(0, num_estimates)
  for (i in 1:num_estimates) {
    estimates[i] <- prob(num_rolls)
  }
  return(estimates)
}


#4:


EstimatesHist <- function(num_estimates = 1000, num_rolls = 1000, title) 
{
  all_estimates <- probEstimates(num_estimates = num_estimates, num_rolls = num_rolls)
  hist(all_estimates,  xlim = c(0.05, 0.3), main = title)
  m <- mean(all_estimates)
  abline(v = m, col = "blue", lwd = 2)
  s <- sd(all_estimates)
  abline(v = c(m - 2*s, m + 2*s), col = "red", lwd = 2, lty = "dashed") 
  print(quantile(all_estimates, prob = c(0.5, 0.75, 0.95, 0.99)))
}




#5:

par(mfrow = c(2,3))

EstimatesHist(num_rolls = 10, title = "Histogram of 1000 Estimates of 10 rolls of Three t do you Fair Dice")
EstimatesHist(num_rolls = 100, title = "Histogram of 1000 Estimates of 100 rolls of Three Fair Dice")
EstimatesHist(title = "Histogram of 1000 Estimates of 1000 rolls of Three Fair Dice")


#6:


EstimatesHist(num_estimates = 10, title = "Histogram of 10 Estimates of 1000 rolls of Three Fair Dice")
EstimatesHist(num_estimates = 100, title = "Histogram of 100 Estimates of 1000 rolls of Three Fair Dice")
EstimatesHist(title = "Histogram of 1000 Estimates of 1000 rolls of Three Fair Dice")








