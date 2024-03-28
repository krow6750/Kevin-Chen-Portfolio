values <- scan("exponential.txt")
pdf("exponential_hist.pdf")
hist(values, breaks = 50, probability = TRUE, col = "lightblue",
     xlab = "x", ylab = "f(x)", main = "Histogram of exponential(1) variates")
curve(dexp(x, rate = 1), add = TRUE, col = "red")
dev.off()
