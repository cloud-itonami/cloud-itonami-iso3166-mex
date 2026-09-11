(ns statute.facts
  "General-law compliance catalog for Mexico (MEX) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  diputados.gob.mx (the Cámara de Diputados' own legislative library,
  usually the first-choice citation source for Mexican federal law) was
  entirely unreachable to WebFetch -- ECONNREFUSED at the IP level
  across the main domain, its portalhcd subdomain, and the wwwmat.sat.gob.mx
  mirror path all failed the same way for two of the three URLs tried,
  a genuine connectivity dead-end distinct from JS-only/403/CAPTCHA.
  All three entries below were instead verified via alternate official
  government mirrors (gob.mx's own CMS upload host, and the
  wwwmat.sat.gob.mx -- Servicio de Administración Tributaria -- mirror
  of the Cámara de Diputados library) that DID render: each PDF's own
  header states its title, Diario Oficial de la Federación (DOF)
  original-publication date, and (where shown) most-recent-reform date.
  Body text in these PDFs renders as boxes for a font-subsetting reason
  (same artifact class as Denmark's Datatilsynet PDF and Berlin's
  IFG/BlnDSG PDFs), but headers/titles are legible.")

(def catalog
  "iso3 -> vector of statute entries."
  {"MEX"
   [{:statute/id "mex.ley-general-de-sociedades-mercantiles-1934"
     :statute/title "Ley General de Sociedades Mercantiles"
     :statute/jurisdiction "MEX"
     :statute/kind :law
     :statute/law-number "DOF 04-08-1934"
     :statute/url "https://wwwmat.sat.gob.mx/cs/Satellite?blobcol=urldata&blobkey=id&blobtable=MungoBlobs&blobwhere=1461172350024&ssbinary=true"
     :statute/url-provenance :official-mexico-gov-mirror
     :statute/enacted-date "1934-08-04"
     :statute/last-revised-date "2016-03-14"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "mex.lfpdppp-2010"
     :statute/title "Ley Federal de Protección de Datos Personales en Posesión de los Particulares"
     :statute/jurisdiction "MEX"
     :statute/kind :law
     :statute/law-number "DOF 05-07-2010"
     :statute/url "https://www.gob.mx/cms/uploads/attachment/file/123648/Ley_Federal_de_Protecci_n_de_Datos_Personales_en_Posesi_n_de_los.pdf"
     :statute/url-provenance :official-mexico-gov-mirror
     :statute/enacted-date "2010-07-05"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "mex.ley-federal-del-trabajo-1970"
     :statute/title "Ley Federal del Trabajo"
     :statute/jurisdiction "MEX"
     :statute/kind :law
     :statute/law-number "DOF 01-04-1970"
     :statute/url "https://www.gob.mx/cms/uploads/attachment/file/156203/1044_Ley_Federal_del_Trabajo.pdf"
     :statute/url-provenance :official-mexico-gov-mirror
     :statute/enacted-date "1970-04-01"
     :statute/last-revised-date "2015-06-12"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-mex statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "MEX")) " MEX statutes seeded with an "
                 "official gob.mx/sat.gob.mx mirror citation (diputados.gob.mx "
                 "itself was unreachable). Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
