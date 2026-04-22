(ns tp04.ej15
  "Ejercicio 15 — Lazy sequences (5 pts). Trazabilidad: F-30")

;; Los primeros n pares positivos (2, 4, 6...).
(defn primeros-n-pares [n]
  ;; TODO: implementar
  (take n (iterate #(+ % 2) 2))
  )

;; Secuencia infinita de Fibonacci. DEBE ser lazy.
(defn fibonacci []
  ;; TODO: implementar con lazy-seq o iterate
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
  )

;; Toma elementos mientras sean menores que umbral.
(defn tomar-mientras-menor [coll umbral]
  ;; TODO: implementar
  (take-while #(< % umbral) coll)
  )
