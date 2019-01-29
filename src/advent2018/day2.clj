(ns advent2018.day2)

(defn map-vals
  "Return the result of applying f to the values of the map kv."
  [f kv]
  (into {} (for [[k v] kv] [k (f v)])))

(defn count-letters [s]
  (->> s
       (seq)
       (group-by identity)
       (map-vals count)))
