
package org.irri.iric.ds.chado.dao;

import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpseekSettings;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpseekSettings entities.
 * 
 */
public interface VSnpseekSettingsDAO extends JpaDao<VSnpseekSettings>, SnpSeekSettingsDAO {

	/**
	 * JPQL Query - findAllVSnpseekSettingss
	 *
	 */
	public Set<VSnpseekSettings> findAllVSnpseekSettingss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpseekSettingss
	 *
	 */
	public Set<VSnpseekSettings> findAllVSnpseekSettingss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByValueContaining
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByValueContaining(String value) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByValueContaining
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByValueContaining(String value, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByNameContaining
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByNameContaining
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByPrimaryKey
	 *
	 */
	public VSnpseekSettings findVSnpseekSettingsByPrimaryKey(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByPrimaryKey
	 *
	 */
	public VSnpseekSettings findVSnpseekSettingsByPrimaryKey(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByName
	 *
	 */
	public VSnpseekSettings findVSnpseekSettingsByName(String name_2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByName
	 *
	 */
	public VSnpseekSettings findVSnpseekSettingsByName(String name_2, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByValue
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByValue(String value_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpseekSettingsByValue
	 *
	 */
	public Set<VSnpseekSettings> findVSnpseekSettingsByValue(String value_1, int startResult, int maxRows)
			throws DataAccessException;

}