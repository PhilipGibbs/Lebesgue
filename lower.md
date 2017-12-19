### Introduction

The task of defining rigorously proven upper limits for the area of Lebesgue's Universal Cover has proven relatively tractable. 
There is even hope that this process is converging or even has converged on the optimal solution. In contrast, the problem of
proving lower limits is still unaproachable except up to a point which appears to be well below optimal. However, it may be 
possible to establish better lower limits if the right hypothesis is assumed. E.g. we may assume that the optimal cover is
a subset of the basic slanted cover. The gap of establishing the validity of that assumption could then be closed later.
Even this goal remains ambitious but a first step would be to define a minimal set of curves of constant width that would
fill the conjectured optimal cover. That is what will be attempted here. Apart from the eventual possibility of proving 
a lower limit, conditional or otherwise, the more immediate motivation for doing this is to uncover any further scope for
bringing down the upper limit.

The shape we will assume is the regular hexagon with two corners removed at _C_ and _E_ at a slant angle _σ_ (see figure 1). This shape will be referred to as 𝒫(_σ_) or just 𝒫.

##### Figure 1
![Figure 1](figures/fig1.png)

## Critial Curves

### Roueleux Triangle and Circle 

The roueleux triangle and the circle can only be fitted into 𝒫 in one way as shown in figure 17. The convex hull (dark grey)
of these two shapes must be part of any cover contained in this area. Therefore no regions can be removed near the corners
_B_, _D_ or _F_. Only the light grey areas have possibilities for reduction of the area. This sets a lower bound for the minimal cover within 𝒫, but it is no better than the general lower bound given by these two shapes.

##### Figure 17
![Figure 17](figures/fig17.png)

### Other Shapes with Unique Fit.

To improve the conditional lower bound we can look for other curves of constant width that fit into 𝒫 in a unique way. The convex hull of all such shapes when fitted will set a new conditional lower bound (i.e. conditional on the assumption that the universal cover of least area fits inside 𝒫(_σ_).)

Recall that any curve of constant width can be placed within 𝒫 using rotations and translations only. Reflections are not necessary to achiev the fit (Baez, Bagdasaryan, Gibbs 2015.) However, reflections are allowed in general and must be counted when considering the number of ways a curve would fit into 𝒫. A curve and its reflection would fit into 𝒫 without further relfections, therefore there will be at least two ways to make the fit, unless the curve has a reflection symmetry.

Curves of constant width can also have a rotation symmetry which must either be of odd order or infinite order (in the case of the circle.) These are special cases that must be considered separately. 

Curves without any rotation symmetry can be fitted into a regular hexagon in _f_ = 6 _k_ ways. This must be a multiple of six because rotations through 60 degrees will provide distinct fits into the hexagon. This six-fold rotational freedom is sufficient to enable a fit inside 𝒫. Therefore the fit can only be unique for curves with no rotation symmetry if _k_ = 1 and _f_ = 6.

Furthermore, once the shape is fitted into the hexagin, there are two main cases to consider according to how the curve enters the corners of the hexagon.


