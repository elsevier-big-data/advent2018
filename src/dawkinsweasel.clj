(ns dawkinsweasel)



(def genes (reduce #(conj %1 (char %2)) [\space] (range 65 91)))
(defn random-individual
  []
  (->> (repeatedly #(rand-nth genes))
       (take 28)
       (apply str)))


(def random-population #(take 100 (repeatedly random-individual)))


(defn compare-individuals
  [x y]
  (count (filter (fn [[a b]] (= a b)) (map vector x y))))


(defn compare-individuals2
  [x y]
  (->> (map vector x y)
       (filter (fn [[a b]] (= a b)))
       count))



