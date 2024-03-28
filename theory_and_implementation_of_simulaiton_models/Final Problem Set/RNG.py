from enum import Enum
from numpy.random import MT19937, Generator
import numpy.typing
import numpy as np
import os

#############################################################################
class Stream(Enum):
    ''' enumeration to identify different streams (one per stochastic component
        in the model) for the random number generator
    '''
    ARRIVAL = 0
    SERVICE_TASK = 1
    SERVICE_PROCESS = 2

######################################################################
class RNG:
    ''' This class implements a wrapper around numpy's MT19937 generator
        to allow for a "streams" implementation, i.e., where we can have a
        different stream of random numbers for each different stochastic
        component.  The stream will be indicated using one of the values
        defined in the Stream enumeration class.  Each wrapper method will do
        the right thing to pull and then update the state of the particular
        stream.
    '''

    # class-level variables
    _seed: numpy.int64 = None
    _streams: list[numpy.random.Generator] = []
    _initialized: bool = False

    ############################################################################
    @classmethod
    def setSeed(cls, seed: numpy.int64) -> None:
        cls._seed = seed
        cls.initializeStreams()

    ############################################################################
    @classmethod
    def initializeStreams(cls) -> None:
        ''' Class-level method to initialize streams for generating random
            numbers.  This uses the .jumped() method to set up the streams
            sufficiently far apart, giving us one stream per stochastic
            component (i.e., number of entries in the Stream enum).

            See:
                https://bit.ly/numpy_random_jumping
                https://bit.ly/numpy_random_Generator
        '''
        cls._streams = []
        rng = MT19937(cls._seed)  # construct a Mersenne Twister with appropriate seed
        for i in range(len(Stream)):
            cls._streams.append(Generator(rng.jumped(i)))
        cls._initialized = True

    @classmethod
    def random(cls, which_stream: Stream) -> np.float64:
        """Generate a random float from a uniform distribution between 0 and 1.

        Parameters:
            which_stream (Stream): Enum value specifying which stream to draw the random number from.

        Returns:
            np.float64: A random float from a uniform distribution between 0 and 1.
        """
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]
        return generator.random()
    
    @classmethod
    def randint(cls, a: int, b: int, which_stream: Stream) -> np.int64:
        """Generate a random integer between a and b-1.

        Parameters:
            a (int): The lower bound (inclusive) of the interval.
            b (int): The upper bound (exclusive) of the interval.
            which_stream (Stream): Enum value specifying which stream to draw the random number from.

        Returns:
            np.int64: A random integer between a and b-1.
        """
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]
        return generator.integers(a, b)
    
    @classmethod
    def uniform(cls, a: float, b: float, which_stream: Stream) -> np.float64:
        """Generate a random float from a uniform distribution between a and b.

        Parameters:
            a (float): The lower bound (inclusive) of the interval.
            b (float): The upper bound (exclusive) of the interval.
            which_stream (Stream): Enum value specifying which stream to draw the random number from.

        Returns:
            np.float64: A random float from a uniform distribution between a and b.
        """
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]
        return generator.uniform(a, b)
    
    @classmethod
    def exponential(cls, mu: float, which_stream: Stream) -> np.float64:
        """Generate a random float from an exponential distribution with parameter mu.

        Parameters:
            mu (float): The rate parameter, which is the inverse of the mean.
            which_stream (Stream): Enum value specifying which stream to draw the random number from.

        Returns:
            np.float64: A random float from an exponential distribution with parameter mu.
        """
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]
        return generator.exponential(1 / mu)


    ############################################################################
    @classmethod
    def geometric(cls, p: float, which_stream: Stream) -> numpy.int64:
        ''' class-level method to generate integer values drawn from a geometric(p)
            distribution, where p corresponds the probability of success on an
            individual trial (see numpy.Generator.geometric)
        Parameters:
            p: floating-point probability of success on any single trial
            which_stream: named entry from Stream class
        Returns:
            a geometric(p) distributed integer value, corresponding to the 
            number of failures before the first success
        '''
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]

        # according to the API for numpy.Generator.geometric, it models the
        # number of _trials_ until the first success, rather than the number of
        # _failures_ before the first success as R does... so we subtract 1
        #     https://bit.ly/numpy_Generator_geometric
        #     https://stat.ethz.ch/R-manual/R-devel/library/stats/html/Geometric.html
        #
        #return generator.geometric(p)
        return generator.geometric(p) - 1  # see comment above
    
    @classmethod
    def gamma(cls, shape: float, scale: float, which_stream: Stream) -> np.float64:
        """Generate a random float from a gamma distribution with parameters shape and scale.

        Parameters:
            shape (float): The shape parameter, also known as the alpha parameter.
            scale (float): The scale parameter, also known as the beta parameter.
            which_stream (Stream): Enum value specifying which stream to draw the random number from.

        Returns:
            np.float64: A random float from a gamma distribution with parameters shape and scale.
        """
        if not cls._initialized:
            cls.initializeStreams()
        generator = cls._streams[which_stream.value]
        return generator.gamma(shape, scale)

def save_variates_to_file(filename, variates):
    with open(filename, 'w') as f:
        for value in variates:
            f.write(f"{value}\n")

###################
def main() -> None:
    # for i in range(10000):
    #     print(RNG.geometric(0.2, Stream.ARRIVAL))

    num_variates = 10000

    # Geometric
    geometric_variates = [RNG.geometric(0.2, Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("geom.txt", geometric_variates)

    # Gamma
    gamma_variates = [RNG.gamma(2, 3, Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("gamma.txt", gamma_variates)

    # Exponential
    exponential_variates = [RNG.exponential(1, Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("exponential.txt", exponential_variates)

    # Uniform
    uniform_variates = [RNG.uniform(0, 1, Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("uniform.txt", uniform_variates)

    # Randint
    randint_variates = [RNG.randint(1, 10, Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("randint.txt", randint_variates)

    # Random
    random_variates = [RNG.random(Stream.ARRIVAL) for _ in range(num_variates)]
    save_variates_to_file("random.txt", random_variates)

if __name__ == "__main__":
    main()
