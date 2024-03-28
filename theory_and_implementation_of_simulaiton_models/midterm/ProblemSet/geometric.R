values = scan("geom.txt")
geom_hist = table(values) / length(values)
pdf("geom_hist.pdf")
plot(geom_hist, xlim = c(0,30), type = "h",
xlab = "x", ylab = "f(x)", bty = "l", las = 1)
points(0:30, dgeom(0:30, 0.2), pch = 20, col = "red")
title("Histogram of geometric(0.2) variates")
dev.off()
