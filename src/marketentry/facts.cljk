(ns marketentry.facts "Mexico market-entry catalog.")
(def catalog
  {"MEX" {:name "United Mexican States"
          :owner-authority "UPCP / CompraNet"
          :legal-basis "LAASSP / LOPSRM"
          :national-spec "CompraNet supplier registration + RFC"
          :provenance "https://upcp-compranet.buengobierno.gob.mx/"
          :required-evidence ["RFC record"
                              "CompraNet registration record"
                              "Mercantile registry extract"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / CompraNet"
          :rep-legal-basis "Mexican legal entity (RFC) typically required for federal procurement participation"
          :rep-provenance "https://upcp-compranet.buengobierno.gob.mx/"
          :corporate-number-owner-authority "SAT"
          :corporate-number-legal-basis "RFC (Registro Federal de Contribuyentes)"
          :corporate-number-provenance "https://www.sat.gob.mx/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "BRA" {:name "Brazil" :owner-authority "Compras.gov.br" :legal-basis "Lei 14.133/2021"
          :national-spec "Compras.gov.br" :provenance "https://www.gov.br/compras/"
          :required-evidence ["CNPJ record" "Compras.gov.br registration" "SICAF record" "Authorized-representative record"]}
   "CAN" {:name "Canada" :owner-authority "CanadaBuys" :legal-basis "GCR"
          :national-spec "CanadaBuys" :provenance "https://canadabuys.canada.ca/"
          :required-evidence ["Business Number (BN) record" "CanadaBuys registration" "GST/HST record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
