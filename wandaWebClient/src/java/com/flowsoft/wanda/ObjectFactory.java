package com.flowsoft.wanda;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.flowsoft.wanda package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _CreateArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createArticle");
	private final static QName _CreateCategory_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createCategory");
	private final static QName _FindUserByUsernameResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findUserByUsernameResponse");
	private final static QName _FindAllCommentFor_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllCommentFor");
	private final static QName _FindArticleByTitleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findArticleByTitleResponse");
	private final static QName _FindAllCommentForResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllCommentForResponse");
	private final static QName _FindAllArticleByUsernameResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticleByUsernameResponse");
	private final static QName _CreateUserResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createUserResponse");
	private final static QName _FindArticleByTitle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findArticleByTitle");
	private final static QName _DeleteCategory_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteCategory");
	private final static QName _FindAllArticleHeader_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticleHeader");
	private final static QName _DeleteArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteArticle");
	private final static QName _EditArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "editArticle");
	private final static QName _FindUserByUsername_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findUserByUsername");
	private final static QName _FindAllArticleHeaderResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticleHeaderResponse");
	private final static QName _Exist_QNAME = new QName(
			"http://wanda.flowsoft.com/", "exist");
	private final static QName _CreateCategoryResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createCategoryResponse");
	private final static QName _EditArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "editArticleResponse");
	private final static QName _CreateUser_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createUser");
	private final static QName _DeleteArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteArticleResponse");
	private final static QName _FindAllArticleByUsername_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticleByUsername");
	private final static QName _DeleteCategoryResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteCategoryResponse");
	private final static QName _ExistResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "existResponse");
	private final static QName _CreateArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createArticleResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.flowsoft.wanda
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link FindAllArticleHeader }
	 * 
	 */
	public FindAllArticleHeader createFindAllArticleHeader() {
		return new FindAllArticleHeader();
	}

	/**
	 * Create an instance of {@link FindUserByUsername }
	 * 
	 */
	public FindUserByUsername createFindUserByUsername() {
		return new FindUserByUsername();
	}

	/**
	 * Create an instance of {@link DeleteArticle }
	 * 
	 */
	public DeleteArticle createDeleteArticle() {
		return new DeleteArticle();
	}

	/**
	 * Create an instance of {@link EditArticle }
	 * 
	 */
	public EditArticle createEditArticle() {
		return new EditArticle();
	}

	/**
	 * Create an instance of {@link FindArticleByTitleResponse }
	 * 
	 */
	public FindArticleByTitleResponse createFindArticleByTitleResponse() {
		return new FindArticleByTitleResponse();
	}

	/**
	 * Create an instance of {@link FindAllCommentForResponse }
	 * 
	 */
	public FindAllCommentForResponse createFindAllCommentForResponse() {
		return new FindAllCommentForResponse();
	}

	/**
	 * Create an instance of {@link FindAllArticleByUsernameResponse }
	 * 
	 */
	public FindAllArticleByUsernameResponse createFindAllArticleByUsernameResponse() {
		return new FindAllArticleByUsernameResponse();
	}

	/**
	 * Create an instance of {@link CreateUserResponse }
	 * 
	 */
	public CreateUserResponse createCreateUserResponse() {
		return new CreateUserResponse();
	}

	/**
	 * Create an instance of {@link CreateArticle }
	 * 
	 */
	public CreateArticle createCreateArticle() {
		return new CreateArticle();
	}

	/**
	 * Create an instance of {@link CreateCategory }
	 * 
	 */
	public CreateCategory createCreateCategory() {
		return new CreateCategory();
	}

	/**
	 * Create an instance of {@link FindUserByUsernameResponse }
	 * 
	 */
	public FindUserByUsernameResponse createFindUserByUsernameResponse() {
		return new FindUserByUsernameResponse();
	}

	/**
	 * Create an instance of {@link FindAllCommentFor }
	 * 
	 */
	public FindAllCommentFor createFindAllCommentFor() {
		return new FindAllCommentFor();
	}

	/**
	 * Create an instance of {@link FindArticleByTitle }
	 * 
	 */
	public FindArticleByTitle createFindArticleByTitle() {
		return new FindArticleByTitle();
	}

	/**
	 * Create an instance of {@link DeleteCategory }
	 * 
	 */
	public DeleteCategory createDeleteCategory() {
		return new DeleteCategory();
	}

	/**
	 * Create an instance of {@link FindAllArticleByUsername }
	 * 
	 */
	public FindAllArticleByUsername createFindAllArticleByUsername() {
		return new FindAllArticleByUsername();
	}

	/**
	 * Create an instance of {@link DeleteArticleResponse }
	 * 
	 */
	public DeleteArticleResponse createDeleteArticleResponse() {
		return new DeleteArticleResponse();
	}

	/**
	 * Create an instance of {@link CreateUser }
	 * 
	 */
	public CreateUser createCreateUser() {
		return new CreateUser();
	}

	/**
	 * Create an instance of {@link EditArticleResponse }
	 * 
	 */
	public EditArticleResponse createEditArticleResponse() {
		return new EditArticleResponse();
	}

	/**
	 * Create an instance of {@link CreateArticleResponse }
	 * 
	 */
	public CreateArticleResponse createCreateArticleResponse() {
		return new CreateArticleResponse();
	}

	/**
	 * Create an instance of {@link ExistResponse }
	 * 
	 */
	public ExistResponse createExistResponse() {
		return new ExistResponse();
	}

	/**
	 * Create an instance of {@link DeleteCategoryResponse }
	 * 
	 */
	public DeleteCategoryResponse createDeleteCategoryResponse() {
		return new DeleteCategoryResponse();
	}

	/**
	 * Create an instance of {@link FindAllArticleHeaderResponse }
	 * 
	 */
	public FindAllArticleHeaderResponse createFindAllArticleHeaderResponse() {
		return new FindAllArticleHeaderResponse();
	}

	/**
	 * Create an instance of {@link CreateCategoryResponse }
	 * 
	 */
	public CreateCategoryResponse createCreateCategoryResponse() {
		return new CreateCategoryResponse();
	}

	/**
	 * Create an instance of {@link Exist }
	 * 
	 */
	public Exist createExist() {
		return new Exist();
	}

	/**
	 * Create an instance of {@link WandaUser }
	 * 
	 */
	public WandaUser createWandaUser() {
		return new WandaUser();
	}

	/**
	 * Create an instance of {@link Category }
	 * 
	 */
	public Category createCategory() {
		return new Category();
	}

	/**
	 * Create an instance of {@link ArticleHeader }
	 * 
	 */
	public ArticleHeader createArticleHeader() {
		return new ArticleHeader();
	}

	/**
	 * Create an instance of {@link Article }
	 * 
	 */
	public Article createArticle() {
		return new Article();
	}

	/**
	 * Create an instance of {@link Comment }
	 * 
	 */
	public Comment createComment() {
		return new Comment();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateArticle }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createArticle")
	public JAXBElement<CreateArticle> createCreateArticle(CreateArticle value) {
		return new JAXBElement<CreateArticle>(_CreateArticle_QNAME,
				CreateArticle.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateCategory }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createCategory")
	public JAXBElement<CreateCategory> createCreateCategory(CreateCategory value) {
		return new JAXBElement<CreateCategory>(_CreateCategory_QNAME,
				CreateCategory.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindUserByUsernameResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findUserByUsernameResponse")
	public JAXBElement<FindUserByUsernameResponse> createFindUserByUsernameResponse(
			FindUserByUsernameResponse value) {
		return new JAXBElement<FindUserByUsernameResponse>(
				_FindUserByUsernameResponse_QNAME,
				FindUserByUsernameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllCommentFor }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllCommentFor")
	public JAXBElement<FindAllCommentFor> createFindAllCommentFor(
			FindAllCommentFor value) {
		return new JAXBElement<FindAllCommentFor>(_FindAllCommentFor_QNAME,
				FindAllCommentFor.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindArticleByTitleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findArticleByTitleResponse")
	public JAXBElement<FindArticleByTitleResponse> createFindArticleByTitleResponse(
			FindArticleByTitleResponse value) {
		return new JAXBElement<FindArticleByTitleResponse>(
				_FindArticleByTitleResponse_QNAME,
				FindArticleByTitleResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllCommentForResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllCommentForResponse")
	public JAXBElement<FindAllCommentForResponse> createFindAllCommentForResponse(
			FindAllCommentForResponse value) {
		return new JAXBElement<FindAllCommentForResponse>(
				_FindAllCommentForResponse_QNAME,
				FindAllCommentForResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllArticleByUsernameResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticleByUsernameResponse")
	public JAXBElement<FindAllArticleByUsernameResponse> createFindAllArticleByUsernameResponse(
			FindAllArticleByUsernameResponse value) {
		return new JAXBElement<FindAllArticleByUsernameResponse>(
				_FindAllArticleByUsernameResponse_QNAME,
				FindAllArticleByUsernameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateUserResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createUserResponse")
	public JAXBElement<CreateUserResponse> createCreateUserResponse(
			CreateUserResponse value) {
		return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME,
				CreateUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindArticleByTitle }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findArticleByTitle")
	public JAXBElement<FindArticleByTitle> createFindArticleByTitle(
			FindArticleByTitle value) {
		return new JAXBElement<FindArticleByTitle>(_FindArticleByTitle_QNAME,
				FindArticleByTitle.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCategory }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "deleteCategory")
	public JAXBElement<DeleteCategory> createDeleteCategory(DeleteCategory value) {
		return new JAXBElement<DeleteCategory>(_DeleteCategory_QNAME,
				DeleteCategory.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllArticleHeader }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticleHeader")
	public JAXBElement<FindAllArticleHeader> createFindAllArticleHeader(
			FindAllArticleHeader value) {
		return new JAXBElement<FindAllArticleHeader>(
				_FindAllArticleHeader_QNAME, FindAllArticleHeader.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteArticle }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "deleteArticle")
	public JAXBElement<DeleteArticle> createDeleteArticle(DeleteArticle value) {
		return new JAXBElement<DeleteArticle>(_DeleteArticle_QNAME,
				DeleteArticle.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link EditArticle }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "editArticle")
	public JAXBElement<EditArticle> createEditArticle(EditArticle value) {
		return new JAXBElement<EditArticle>(_EditArticle_QNAME,
				EditArticle.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindUserByUsername }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findUserByUsername")
	public JAXBElement<FindUserByUsername> createFindUserByUsername(
			FindUserByUsername value) {
		return new JAXBElement<FindUserByUsername>(_FindUserByUsername_QNAME,
				FindUserByUsername.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllArticleHeaderResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticleHeaderResponse")
	public JAXBElement<FindAllArticleHeaderResponse> createFindAllArticleHeaderResponse(
			FindAllArticleHeaderResponse value) {
		return new JAXBElement<FindAllArticleHeaderResponse>(
				_FindAllArticleHeaderResponse_QNAME,
				FindAllArticleHeaderResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Exist }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "exist")
	public JAXBElement<Exist> createExist(Exist value) {
		return new JAXBElement<Exist>(_Exist_QNAME, Exist.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateCategoryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createCategoryResponse")
	public JAXBElement<CreateCategoryResponse> createCreateCategoryResponse(
			CreateCategoryResponse value) {
		return new JAXBElement<CreateCategoryResponse>(
				_CreateCategoryResponse_QNAME, CreateCategoryResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link EditArticleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "editArticleResponse")
	public JAXBElement<EditArticleResponse> createEditArticleResponse(
			EditArticleResponse value) {
		return new JAXBElement<EditArticleResponse>(_EditArticleResponse_QNAME,
				EditArticleResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createUser")
	public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
		return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteArticleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "deleteArticleResponse")
	public JAXBElement<DeleteArticleResponse> createDeleteArticleResponse(
			DeleteArticleResponse value) {
		return new JAXBElement<DeleteArticleResponse>(
				_DeleteArticleResponse_QNAME, DeleteArticleResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllArticleByUsername }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticleByUsername")
	public JAXBElement<FindAllArticleByUsername> createFindAllArticleByUsername(
			FindAllArticleByUsername value) {
		return new JAXBElement<FindAllArticleByUsername>(
				_FindAllArticleByUsername_QNAME,
				FindAllArticleByUsername.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteCategoryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "deleteCategoryResponse")
	public JAXBElement<DeleteCategoryResponse> createDeleteCategoryResponse(
			DeleteCategoryResponse value) {
		return new JAXBElement<DeleteCategoryResponse>(
				_DeleteCategoryResponse_QNAME, DeleteCategoryResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ExistResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "existResponse")
	public JAXBElement<ExistResponse> createExistResponse(ExistResponse value) {
		return new JAXBElement<ExistResponse>(_ExistResponse_QNAME,
				ExistResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateArticleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "createArticleResponse")
	public JAXBElement<CreateArticleResponse> createCreateArticleResponse(
			CreateArticleResponse value) {
		return new JAXBElement<CreateArticleResponse>(
				_CreateArticleResponse_QNAME, CreateArticleResponse.class,
				null, value);
	}

}
