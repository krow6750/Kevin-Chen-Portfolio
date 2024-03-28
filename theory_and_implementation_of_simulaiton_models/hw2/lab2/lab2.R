#============INSTALLING LIBRARIES============
install.packages("simEd")
install.packages("tidyverse")

#============LOADING LIBRARIES============
library("simEd")
library("tidyverse")

#============DEFINING FUNCTIONS============
getSvc1 <- function() { rgamma(1, shape = 1.0, scale = 0.9) }
getSvc2 <- function() { rgamma(1, shape = 1.05, scale = 0.9) }
getSvc3 <- function() { rgamma(1, shape = 1.1, scale = 0.9) }

#============SIMULATING (M/M/1)============

#initializing lists
list <- c()
xlist <- c()

#making a list to store x-axis values
for (i in seq(100,5000, by=100)){
  xlist[[i]] <- i
}

xAxis<-compact(xlist)

#making lists to store the average sojourn time
for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1234321, showOutput=FALSE)
  list[[i]] <- seed$avgSojourn
}

seed1<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 5551555, showOutput=FALSE)
  list[[i]] <- seed$avgSojourn
}

seed2<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1111111, showOutput=FALSE)
  list[[i]] <- seed$avgSojourn
}

seed3<-compact(list)

#plotting the average sojourn time
plot(unlist(xAxis), unlist(seed1), ylim=c(0, 20), xlim=c(0, 5000), xaxt="n", xlab="jobs", ylab="average sojourn", axes=FALSE)
axis(1, at = seq(0, 5000, by = 500))
axis(2)
points(unlist(xAxis), unlist(seed2), pch=23)
points(unlist(xAxis), unlist(seed3), pch=16)
abline(h=9, col="black")
legend(x = 50, y = 20,
       title = "Initial Seed",
       legend = c("1234321", "5551555", "1111111"),
       pch=c(1, 23, 16),
       cex=1)

#============SIMULATING (M/G/1) SIGMA 1============

#making lists to store the average sojourn time
for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1234321, showOutput=FALSE, serviceFcn = getSvc1)
  list[[i]] <- seed$avgSojourn
}

seed1<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 5551555, showOutput=FALSE, serviceFcn = getSvc1)
  list[[i]] <- seed$avgSojourn
}

seed2<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1111111, showOutput=FALSE, serviceFcn = getSvc1)
  list[[i]] <- seed$avgSojourn
}

seed3<-compact(list)

#plotting the average sojourn time
plot(unlist(xAxis), unlist(seed1), ylim=c(0, 20), xlim=c(0, 5000), xaxt="n", xlab="jobs", ylab="average sojourn", axes=FALSE)
axis(1, at = seq(0, 5000, by = 500))
axis(2)
points(unlist(xAxis), unlist(seed2), pch=23)
points(unlist(xAxis), unlist(seed3), pch=16)
abline(h=9, col="black")
legend(x = 50, y = 20,
       title = "Initial Seed",
       legend = c("1234321", "5551555", "1111111"),
       pch=c(1, 23, 16),
       cex=1)

#============SIMULATING (M/G/1) SIGMA 2============

#making lists to store the average sojourn time
for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1234321, showOutput=FALSE, serviceFcn = getSvc2)
  list[[i]] <- seed$avgSojourn
}

seed1<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 5551555, showOutput=FALSE, serviceFcn = getSvc2)
  list[[i]] <- seed$avgSojourn
}

seed2<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1111111, showOutput=FALSE, serviceFcn = getSvc2)
  list[[i]] <- seed$avgSojourn
}

seed3<-compact(list)

#plotting the average sojourn time
plot(unlist(xAxis), unlist(seed1), ylim=c(0, 35), xlim=c(0, 5000), xaxt="n", xlab="jobs", ylab="average sojourn", axes=FALSE)
axis(1, at = seq(0, 5000, by = 500))
axis(2)
points(unlist(xAxis), unlist(seed2), pch=23)
points(unlist(xAxis), unlist(seed3), pch=16)
abline(h=(189/11), col="black")
legend(x = 0, y = 35,
       title = "Initial Seed",
       legend = c("1234321", "5551555", "1111111"),
       pch=c(1, 23, 16),
       cex=1)

#============SIMULATING (M/G/1) SIGMA 3============

#making lists to store the average sojourn times
for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1234321, showOutput=FALSE, serviceFcn = getSvc3)
  list[[i]] <- seed$avgSojourn
}

seed1<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 5551555, showOutput=FALSE, serviceFcn = getSvc3)
  list[[i]] <- seed$avgSojourn
}

seed2<-compact(list)

for (i in seq(100,5000, by=100)){
  seed<-ssq(maxArrivals = i, seed = 1111111, showOutput=FALSE, serviceFcn = getSvc3)
  list[[i]] <- seed$avgSojourn
}

seed3<-compact(list)

#plotting the average sojourn times
plot(unlist(xAxis), unlist(seed1), ylim=c(0, 100), xlim=c(0, 5000), xaxt="n", xlab="jobs", ylab="average sojourn", axes=FALSE)
axis(1, at = seq(0, 5000, by = 500))
axis(2)
points(unlist(xAxis), unlist(seed2), pch=23)
points(unlist(xAxis), unlist(seed3), pch=16)
abline(h=99, col="black")
legend(x = 0, y = 80,
       title = "Initial Seed",
       legend = c("1234321", "5551555", "1111111"),
       pch=c(1, 23, 16),
       cex=1)