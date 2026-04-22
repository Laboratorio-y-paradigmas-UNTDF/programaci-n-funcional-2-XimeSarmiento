(ns tp04.ej07
  "Ejercicio 7 — Partial en Clojure (5 pts). Trazabilidad: F-15"
  (:require [clojure.string :as str]))

;; Retorna {:status :ok :value value} si no vacío, {:status :error :error "FIELD es obligatorio"}.
(defn required-field [field-name value]
  ;; TODO: implementar
  (if (and(not (nil? value)) (not (str/blank? value)))
    {:status :ok :value value}
    {:status :error :error (str field-name " es obligatorio")})
  )

(def doble
  ;; TODO: (partial * 2)
  (partial * 2)
  )

(def triple
  ;; TODO: (partial * 3)
  (partial * 3)
  )

(def validate-name
  ;; TODO: (partial required-field "nombre")
  (partial required-field "nombre")
  )

(def validate-email
  ;; TODO: (partial required-field "email")
  (partial required-field "email")
  )
