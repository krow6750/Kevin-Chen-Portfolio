values <- scan("random.txt")
pdf("random_hist.pdf")
hist(values, breaks = 50, probability = TRUE, col = "lightblue",
     xlab = "x", ylab = "f(x)", main = "Histogram of random variates")
curve(dunif(x, min = 0, max = 1), add = TRUE, col = "red")
dev.off()
