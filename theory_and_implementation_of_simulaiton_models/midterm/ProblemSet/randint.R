values <- scan("randint.txt")
randint_hist <- table(values) / length(values)
pdf("randint_hist.pdf")
plot(randint_hist, type = "h", col = "lightblue",
     xlab = "x", ylab = "f(x)", main = "Histogram of randint(1, 10) variates")
dev.off()
