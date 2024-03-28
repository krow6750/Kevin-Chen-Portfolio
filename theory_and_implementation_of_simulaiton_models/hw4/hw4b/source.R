#Name: Kevin Chen
#Assignment: HW4, generating varieties
#Course: DCS 307
#Instructor: Barry Lawson

#SUM OF UNIFORMS METHOD:
sumOfUniforms <- function(n = 12) {
  uniform_samples <- runif(n)
  return ((sum(uniform_samples)-(n/2))/(sqrt( n / 12)) )
}

normalSamples <- replicate(5000, sumOfUniforms(12))

hist(
  normalSamples, main = "Histogram of Normal (0, 1) Samples",
  ylab = "Frequency Density",
  xlab = "Sample Value",
  freq = FALSE,
  ylim=c(0,0.4),
  xlim=c(-4,4)
)

x <- seq(-4, 4, length.out = 100)
y <- dnorm(x, mean = 0, sd = 1)
lines(x, y, col = "red", lwd = 2)

#BOX-MULLER METHOD:
boxMuller <- function() {
  if (exists("u1")) {
    z2 <- sqrt(-2 * log(u1)) * cos(2 * pi * u2)
    rm(u1, u2)
    return(z2)
  } else {
    u1 <- runif(1)
    u2 <- runif(1)
    z1 <- sqrt(-2 * log(u1)) * sin(2 * pi * u2)
    return(z1)
  }
}

normalSamples <- numeric(5000)
for (i in 1:5000) {
  normalSamples[i] <- boxMuller()
}

hist(
  normalSamples, main = "Histogram of Normal (0, 1) Samples",
     ylab = "Frequency Density",
     xlab = "Sample Value",
     freq = FALSE,
     ylim=c(0,0.4),
     xlim=c(-4,4)
)

x <- seq(-4, 4, length.out = 100)
y <- dnorm(x, mean = 0, sd = 1)
lines(x, y, col = "red", lwd = 2)
