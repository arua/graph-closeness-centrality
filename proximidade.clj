(ns proximidade) 

(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn get-edge [edge]
  (map read-string (str/split edge #" ")))

(defn  add-edge [al, s]
  (let [[v1 v2] (get-edge s)]
    (assoc al v1 (set(conj (al v1) v2)))))

(defn bf_shortest_path [v adjlist]
  (loop [distances (assoc (zipmap (keys adjlist) (repeat nil)) v 0)
         unvisited (adjlist v)
         depth 1]
    (if (empty? unvisited)
      distances
      (let [distances (merge distances (zipmap unvisited (repeat depth)))
            unvisited (filter #(nil? (distances %)) (set (mapcat adjlist unvisited)))
            depth (inc depth)]
        (recur distances unvisited depth)))))

(defn closeness [v adjlist]
  (/ 1 (reduce + (vals (bf_shortest_path v adjlist)))))

(with-open [rdr (clojure.java.io/reader "edges")]
  (as-> (line-seq rdr) input
        (reduce add-edge {} input)
        (zipmap (keys input)
                (map #(closeness % input) (keys input)))
        (sort-by val input)
        (prn (keys input))))
