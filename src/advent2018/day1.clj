(ns advent2018.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))


(def problem-input
  (->> (string/split-lines (string/trim (slurp (io/resource "day1.txt"))))))


(def problem-numbers (map #(Integer/parseInt %) problem-input))

(defn sum-numbers
  "Sums all the given numbers specified"
  [xs]
  (reduce + xs))

(defn total-numbers
  "docstring"
  [xs]
  (reductions + xs))

(defn count-things
  [xs]
  (reduce (fn [acc x]
            (if (acc x)
              (reduced x)
              (conj acc x)))
          #{}
          xs))

(comment

  (count-things (total-numbers (cycle problem-numbers))))
