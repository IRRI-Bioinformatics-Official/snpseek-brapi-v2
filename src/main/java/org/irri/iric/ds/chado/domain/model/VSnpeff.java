package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.object.SnpEffectAnn;
import org.irri.iric.ds.utils.DbUtils;


/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVSnpeffs", query = "select myVSnpeff from VSnpeff myVSnpeff"),
		@NamedQuery(name = "findVSnpeffByAnnotation", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.annotation = ?1"),
		@NamedQuery(name = "findVSnpeffByAnnotationContaining", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.annotation like ?1"),
		@NamedQuery(name = "findVSnpeffByChromosome", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.chromosome = ?1"),
		@NamedQuery(name = "findVSnpeffByPosition", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.position = ?1"),
		@NamedQuery(name = "findVSnpeffByPositionBetween", query = "select myVSnpeff from VSnpeff myVSnpeff where  myVSnpeff.chromosome = ?1 and myVSnpeff.position between ?2 and ?3 order by myVSnpeff.position"),
		@NamedQuery(name = "findVSnpeffByPositionBetweenSnpsets", query = "select myVSnpeff from VSnpeff myVSnpeff where  myVSnpeff.chromosome = ?1 and myVSnpeff.position between ?2 and ?3 and myVSnpeff.variantset in (?4) order by myVSnpeff.position"),
		@NamedQuery(name = "findVSnpeffByPrimaryKey", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpeffBySnpFeatureId", query = "select myVSnpeff from VSnpeff myVSnpeff where myVSnpeff.snpFeatureId = ?1") })
@Table(name = "V_SNPEFF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VSnpeff")
public class VSnpeff implements Serializable, SnpsEffect {

	@Override
	public String getSnpset() {
		
		return variantset;
	}

	private static final long serialVersionUID = 1L;

	@Transient
	private Double score;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "CHROMOSOME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chromosome;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "ANNOTATION", length = 2054)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String annotation;

	@Column(name = "VARIANTSET", length = 2054)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String variantset;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setChromosome(Integer chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public Integer getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	/**
	 */
	public String getAnnotation() {
		return this.annotation;
	}

	/**
	 */
	public VSnpeff() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpeff that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());
		setAnnotation(that.getAnnotation());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		/*
		 * StringBuilder buffer = new StringBuilder();
		 * 
		 * buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		 * buffer.append("chromosome=[").append(chromosome).append("] ");
		 * buffer.append("position=[").append(position).append("] ");
		 * buffer.append("annotation=[").append(annotation).append("] ");
		 * 
		 * return buffer.toString();
		 */
		return annotation;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((snpFeatureId == null) ? 0 :
		// snpFeatureId.hashCode()));
		result = (int) (prime * result + ((chromosome == null) ? 0 : chromosome.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
		/*
		 * if (obj == this) return true; if (!(obj instanceof VSnpeff)) return false;
		 * VSnpeff equalCheck = (VSnpeff) obj; if ((snpFeatureId == null &&
		 * equalCheck.snpFeatureId != null) || (snpFeatureId != null &&
		 * equalCheck.snpFeatureId == null)) return false; if (snpFeatureId != null &&
		 * !snpFeatureId.equals(equalCheck.snpFeatureId)) return false; return true;
		 */
	}

	private Map<String, String[]> getInfos() {
		Map<String, String[]> mapInfos = null;
		if (mapInfos == null)
			mapInfos = new HashMap();
		if (this.annotation != null) {
			String infos[] = annotation.split(";");
			for (int i = 0; i < infos.length; i++) {
				if (infos[i].startsWith("ANN="))
					mapInfos.put("ANN", infos[i].replace("ANN=", "").split(","));
				else if (infos[i].startsWith("LOF="))
					mapInfos.put("LOF", infos[i].replace("LOF=", "").split("\\|"));
				else if (infos[i].startsWith("NMD="))
					mapInfos.put("NMD", infos[i].replace("NMD=", "").split("\\|"));
			}
		}

		return mapInfos;
	}

	@Override
	public String[] getANN() {
		return getInfos().get("ANN");
	}

	@Override
	public String[] getLOF() {
		
		return getInfos().get("LOF");
	}

	@Override
	public String[] getNMD() {
		
		return getInfos().get("NMD");
	}

	@Override
	public String getContig() {
		
		if (chromosome > 9)
			return "chr" + chromosome;
		return "chr0" + chromosome;
	}

	/*
	 * @Override public String getRefnuc() { 
	 * return null; }
	 */
	@Override
	public Long getChr() {
		
		return Long.valueOf(this.chromosome);
	}

	@Override
	public int compareTo(Object arg0) {
		
		Locus p = (Locus) arg0;
		int ret = getChr().compareTo(p.getChr());
		if (ret != 0)
			return ret;
		ret = getFmin().compareTo(p.getFmin());
		return ret;

	}

	@Override
	public String getUniquename() {
		
		return "snp-" + getContig() + "-" + getPosition();
	}

	@Override
	public Integer getFmin() {
		
		return this.position.intValue();
	}

	@Override
	public Integer getFmax() {
		
		return this.position.intValue();
	}

	@Override
	public Integer getStrand() {
		
		return 1;
	}

	@Override
	public String getDescription() {
		
		return this.annotation;
	}

	@Override
	public BigDecimal getFeatureId() {
		
		return snpFeatureId;
	}

	@Transient
	@Override
	public List<SnpEffectAnn> getANNObj() {
		

		List listAnns = new ArrayList();
		String anns[] = getANN();
		for (int i = 0; i < anns.length; i++) {
			listAnns.add(new SnpEffectAnn(anns[i]));
		}
		return listAnns;
	}

	@Override
	@Transient
	public Double getScore() {
		
		if (score == null) {
			int minrank = Integer.MAX_VALUE;
			Iterator<SnpEffectAnn> itanns = getANNObj().iterator();
			while (itanns.hasNext()) {
				try {
					String annots[] = itanns.next().getAnnotation().split("&");
					for (int in = 0; in < annots.length; in++) {
						int rank = DbUtils.getMapVariantRank(annots[in]);
						if (rank < minrank)
							minrank = rank;
					}
				} catch (Exception ex) {
					//AppContext.debug("annotation=" + annotation);
					ex.printStackTrace();
				}

			}
			score = Double.valueOf(minrank);
		}
		return score;

	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
