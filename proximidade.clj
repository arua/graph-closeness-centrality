(ns proximidade) 

(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn get-edge [edge]
  (map read-string (str/split edge #" ")))

(defn  add-edge [al, edge]
  (let [v (get-edge edge) v1 (first v) v2 (second v)]
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

(with-open [rdr (clojure.java.io/reader "edges")]
  (->> (line-seq rdr)
       (reduce add-edge {})
       (def lol))
       (prn (keys (sort-by val (zipmap (keys lol) (map #(/ 1 (reduce + (vals (bf_shortest_path % lol)))) (keys lol)))))))

