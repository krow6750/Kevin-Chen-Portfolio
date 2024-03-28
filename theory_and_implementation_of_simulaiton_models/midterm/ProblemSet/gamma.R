values <- scan("gamma.txt")
pdf("gamma_hist.pdf")
hist(values, breaks = 50, probability = TRUE, col = "lightblue",
     xlab = "x", ylab = "f(x)", main = "Histogram of gamma(2, 3) variates")
curve(dgamma(x, shape = 2, scale = 3), add = TRUE, col = "red")
dev.off()