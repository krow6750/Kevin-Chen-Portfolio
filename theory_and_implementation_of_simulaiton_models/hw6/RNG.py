from enum import Enum
from numpy.random import MT19937, Generator
import numpy.typing

#############################################################################
class Stream(Enum):
    ''' enumeration to identify different streams (one per stochastic component
        in the model) for the random number generator
    '''
    ARRIVAL    = 0
    COMPLETION = 1

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

###################
def main() -> None:
    for i in range(10000):
        print(RNG.geometric(0.2, Stream.ARRIVAL))

if __name__ == "__main__":
    main()

