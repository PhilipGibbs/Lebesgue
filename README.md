# Lebesgue
Lebesgue's Universal Covering Problem

# Definitions
Lebesgue's universal covering problem seeks a convex planar shape of minimum area that can cover any shape of unit diameter.

The diameter of a shape is the least upper bound (supremum) on the distance between any two points in the shape.
For a shape C To cover a shape S means that C conatains a subset congruent to S. In other words we can rotate, translate and/or reflect S so that it fits inside C.
A universal cover for shapes of diameter one is a shape that covers all such shapes.
Henri Lebesgue posed this problem in a letter to Pal in 1914 (Pal, 1920).

# Goals
The ambitious goal of this project is to solve this problem. More relaistic minor goals are:

- To find and prove an improved upper bound
- To find an improved lower bound conditional on some assumptions about the form of the answer.
- To find a bound that is likely to be the best bound
- To solve the simpler translation cover problem.

# Variants
Variants of the problem that are also of interest and which may be worked on here involve combinations of
  dropping the convexity condition
  dropping the ability to use reflections in covering
  dropping the ability to use rotations in covering (e.g. look for a pure translation cover)
  
# related problems
The problem is one case of a family of problems that seek minimum covers for sets of shapes.
In different cases minimum can mean minimum area, perimeter, diameter, mean width etc. 
Covering can allow different transformations e.g. combinations of translations, rotations and reflections.
The set of shapes to cover can be shapes with fixed diameter or perimeter or curves of fixed length.
Simpler problems have also been studied, e.g. to find the minimum cover for all triangles of given perimeter and/or to restrict the covering shape to be a triangle, rectangle or even a square.

Possibly the hardest and most famous variant in the plane is Moser's Worm Problem which seeks the minimal (convex) planar cover for an open curve of length one. Rotations and translations (not reflections) are allowed. Solving Moser's worm problem is considered "unapproachable" The results obtained so far are ingenious but nevertheless weak.

A wider class of related problems would include the Moving Sofa Problem and the Kakeya Set.
Bellman's Lost in a Forest problem is closely related to Moser's Worm Problem so the research area of search games is also relevant making this class of problems important in applied mathematics rather than simply recreational mathematics. General techniques for solving any of these problems may have significant future appliactions e.g. in robotics or nano-technology.

# Known results

A shape a diameter one or less can always be contained within a closed curve of constant width one. Although this seems intuitively obvious it is not straightforward to prove (Pal 1920, Vrecica 1981). It is therefore necessary and sufficient to seek a universal cover for curves of constant width one. This observation is used in many of the arguments for an upper bound.

For the convex case the  Blaschke Selection Theorem can be used to prove that a minimal cover exists (Kelly, Weiss, 1979, Kovalev 1985)
I.e. there is a cover which has minimum area. It is obvious that a minimum area exists but it is not trivial that this is attained. It is still unproven for the non-convex case. Even in the convex case it is not known if the minimal cover is unique up to congruence. 

The smallest known universal cover has an area of 0.8441153 which is therefore the best upper bound on the minimal area (Baez, Bagdasaryan, Gibbs 2015) The best known lower bound is 0.832 (Brass, Sharifi 2005)

# Intermediate Statements
Numerical Calculations suggest that any of the following statements may be true

- The minimal area cover for any subset of shapes of diameter one is contained inside a hexagonal parallelogon.
- The minimal area (translation) cover for any subset of shapes of diameter one is contained by a strip of diamter one
- The minimal area (translation) cover for any subset of shapes of diameter one is contained in a rhombus whose parallel sides are a unit distance apart
- The minimal cover for all shapes of diameter one is contained in a regular hexagon with an inscribed circle of unit diameter.
- The minimal cover translation cover is contained in a square of side one 
- The minimal cover for all shapes of diameter one is contained in a regular hexagon with an inscribed circle of unit diameter with two corners cut off by the edges of a congruent hexagon with the same centre.

Any of these statements may be treated as a conjecture for which a proof can be attempted or an assumption on which a conditional proof can be based. If both these steps are achieved for a single statement then it is an intermediate step in a complete proof.

# References
see [References](../references.md)

