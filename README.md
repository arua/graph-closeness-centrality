# Closeness Centrality in Clojure - C3

Implementation of [closeness centrality](https://en.wikipedia.org/wiki/Closeness_centrality) for unweighted graphs in Clojure.
Uses a [breadth-first search (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search) to determine the distance between nodes.
The script read the contents of a file called "edges", placed on the same directory as the script.
Returns a list of nodes sorted by closeness.
