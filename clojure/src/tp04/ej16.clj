(ns tp04.ej16
  "Ejercicio 16 — DSL data-driven (5 pts). Trazabilidad: F-31"
  (:require [clojure.string :as str]))

;; Vector de reglas: {:field :name, :pred fn, :msg "..."}
(def user-rules
  ;; TODO: definir al menos 3 reglas (nombre no vacío, email con @, edad >= 18)
  [{:field :name
    :pred (fn [value] (and (string? value) (not (str/blank? value))))
    :msg "nombre vacío"}
   {:field :email
    :pred (fn [value] (and (string? value) (str/includes? value "@")))
    :msg "email inválido"}
   {:field :age
    :pred (fn [value] (and (number? value) (>= value 18)))
    :msg "edad debe ser >= 18"}])

;; Aplica todas las reglas a data. Retorna vector de {:field :error} (vacío si ok).
(defn validate [rules data]
  ;; TODO: implementar
  (reduce
   (fn [errors {:keys [field pred msg]}]
     (if (pred (get data field))
       errors
       (conj errors {:field field :error msg})))
   []
   rules)
  )

;; true si no hay errores.
(defn valid? [rules data]
  ;; TODO: implementar
  (empty? (validate rules data))
  )
