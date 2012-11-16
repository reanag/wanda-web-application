package com.flowsoft.wanda;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.flowsoft.domain.Article;
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

	private final static QName _CreateCategory_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createCategory");
	private final static QName _FindArticleByTitleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findArticleByTitleResponse");
	private final static QName _FindArticleByTitle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findArticleByTitle");
	private final static QName _GetArticleContentByTitle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "getArticleContentByTitle");
	private final static QName _InitTestDbResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "initTestDbResponse");
	private final static QName _CreateUser_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createUser");
	private final static QName _ExistResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "existResponse");
	private final static QName _DeleteCategoryResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteCategoryResponse");
	private final static QName _CreateArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createArticleResponse");
	private final static QName _CreateArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createArticle");
	private final static QName _FindByUsername_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findByUsername");
	private final static QName _FindAllCommentFor_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllCommentFor");
	private final static QName _FindByUsernameResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findByUsernameResponse");
	private final static QName _FindAllCommentForResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllCommentForResponse");
	private final static QName _CreateUserResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createUserResponse");
	private final static QName _DeleteCategory_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteCategory");
	private final static QName _FindAllUser_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllUser");
	private final static QName _InitTestDb_QNAME = new QName(
			"http://wanda.flowsoft.com/", "initTestDb");
	private final static QName _FindAllUserResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllUserResponse");
	private final static QName _GetArticleContentByTitleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "getArticleContentByTitleResponse");
	private final static QName _DeleteArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteArticle");
	private final static QName _EditArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "editArticle");
	private final static QName _FindAllArticle_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticle");
	private final static QName _Exist_QNAME = new QName(
			"http://wanda.flowsoft.com/", "exist");
	private final static QName _CreateCategoryResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "createCategoryResponse");
	private final static QName _EditArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "editArticleResponse");
	private final static QName _FindAllArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "findAllArticleResponse");
	private final static QName _DeleteArticleResponse_QNAME = new QName(
			"http://wanda.flowsoft.com/", "deleteArticleResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.flowsoft.wanda
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetArticleContentByTitleResponse }
	 * 
	 */
	public GetArticleContentByTitleResponse createGetArticleContentByTitleResponse() {
		return new GetArticleContentByTitleResponse();
	}

	/**
	 * Create an instance of {@link InitTestDb }
	 * 
	 */
	public InitTestDb createInitTestDb() {
		return new InitTestDb();
	}

	/**
	 * Create an instance of {@link FindAllUser }
	 * 
	 */
	public FindAllUser createFindAllUser() {
		return new FindAllUser();
	}

	/**
	 * Create an instance of {@link FindAllUserResponse }
	 * 
	 */
	public FindAllUserResponse createFindAllUserResponse() {
		return new FindAllUserResponse();
	}

	/**
	 * Create an instance of {@link EditArticle }
	 * 
	 */
	public EditArticle createEditArticle() {
		return new EditArticle();
	}

	/**
	 * Create an instance of {@link DeleteArticle }
	 * 
	 */
	public DeleteArticle createDeleteArticle() {
		return new DeleteArticle();
	}

	/**
	 * Create an instance of {@link CreateUserResponse }
	 * 
	 */
	public CreateUserResponse createCreateUserResponse() {
		return new CreateUserResponse();
	}

	/**
	 * Create an instance of {@link FindAllCommentForResponse }
	 * 
	 */
	public FindAllCommentForResponse createFindAllCommentForResponse() {
		return new FindAllCommentForResponse();
	}

	/**
	 * Create an instance of {@link FindByUsernameResponse }
	 * 
	 */
	public FindByUsernameResponse createFindByUsernameResponse() {
		return new FindByUsernameResponse();
	}

	/**
	 * Create an instance of {@link CreateArticle }
	 * 
	 */
	public CreateArticle createCreateArticle() {
		return new CreateArticle();
	}

	/**
	 * Create an instance of {@link FindAllCommentFor }
	 * 
	 */
	public FindAllCommentFor createFindAllCommentFor() {
		return new FindAllCommentFor();
	}

	/**
	 * Create an instance of {@link FindByUsername }
	 * 
	 */
	public FindByUsername createFindByUsername() {
		return new FindByUsername();
	}

	/**
	 * Create an instance of {@link DeleteCategory }
	 * 
	 */
	public DeleteCategory createDeleteCategory() {
		return new DeleteCategory();
	}

	/**
	 * Create an instance of {@link DeleteArticleResponse }
	 * 
	 */
	public DeleteArticleResponse createDeleteArticleResponse() {
		return new DeleteArticleResponse();
	}

	/**
	 * Create an instance of {@link EditArticleResponse }
	 * 
	 */
	public EditArticleResponse createEditArticleResponse() {
		return new EditArticleResponse();
	}

	/**
	 * Create an instance of {@link FindAllArticleResponse }
	 * 
	 */
	public FindAllArticleResponse createFindAllArticleResponse() {
		return new FindAllArticleResponse();
	}

	/**
	 * Create an instance of {@link FindAllArticle }
	 * 
	 */
	public FindAllArticle createFindAllArticle() {
		return new FindAllArticle();
	}

	/**
	 * Create an instance of {@link Exist }
	 * 
	 */
	public Exist createExist() {
		return new Exist();
	}

	/**
	 * Create an instance of {@link CreateCategoryResponse }
	 * 
	 */
	public CreateCategoryResponse createCreateCategoryResponse() {
		return new CreateCategoryResponse();
	}

	/**
	 * Create an instance of {@link GetArticleContentByTitle }
	 * 
	 */
	public GetArticleContentByTitle createGetArticleContentByTitle() {
		return new GetArticleContentByTitle();
	}

	/**
	 * Create an instance of {@link FindArticleByTitleResponse }
	 * 
	 */
	public FindArticleByTitleResponse createFindArticleByTitleResponse() {
		return new FindArticleByTitleResponse();
	}

	/**
	 * Create an instance of {@link CreateCategory }
	 * 
	 */
	public CreateCategory createCreateCategory() {
		return new CreateCategory();
	}

	/**
	 * Create an instance of {@link FindArticleByTitle }
	 * 
	 */
	public FindArticleByTitle createFindArticleByTitle() {
		return new FindArticleByTitle();
	}

	/**
	 * Create an instance of {@link CreateUser }
	 * 
	 */
	public CreateUser createCreateUser() {
		return new CreateUser();
	}

	/**
	 * Create an instance of {@link CreateArticleResponse }
	 * 
	 */
	public CreateArticleResponse createCreateArticleResponse() {
		return new CreateArticleResponse();
	}

	/**
	 * Create an instance of {@link DeleteCategoryResponse }
	 * 
	 */
	public DeleteCategoryResponse createDeleteCategoryResponse() {
		return new DeleteCategoryResponse();
	}

	/**
	 * Create an instance of {@link ExistResponse }
	 * 
	 */
	public ExistResponse createExistResponse() {
		return new ExistResponse();
	}

	/**
	 * Create an instance of {@link InitTestDbResponse }
	 * 
	 */
	public InitTestDbResponse createInitTestDbResponse() {
		return new InitTestDbResponse();
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
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetArticleContentByTitle }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "getArticleContentByTitle")
	public JAXBElement<GetArticleContentByTitle> createGetArticleContentByTitle(
			GetArticleContentByTitle value) {
		return new JAXBElement<GetArticleContentByTitle>(
				_GetArticleContentByTitle_QNAME,
				GetArticleContentByTitle.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link InitTestDbResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "initTestDbResponse")
	public JAXBElement<InitTestDbResponse> createInitTestDbResponse(
			InitTestDbResponse value) {
		return new JAXBElement<InitTestDbResponse>(_InitTestDbResponse_QNAME,
				InitTestDbResponse.class, null, value);
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
	 * Create an instance of {@link JAXBElement }{@code <}{@link FindByUsername }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findByUsername")
	public JAXBElement<FindByUsername> createFindByUsername(FindByUsername value) {
		return new JAXBElement<FindByUsername>(_FindByUsername_QNAME,
				FindByUsername.class, null, value);
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
	 * {@link FindByUsernameResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findByUsernameResponse")
	public JAXBElement<FindByUsernameResponse> createFindByUsernameResponse(
			FindByUsernameResponse value) {
		return new JAXBElement<FindByUsernameResponse>(
				_FindByUsernameResponse_QNAME, FindByUsernameResponse.class,
				null, value);
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
	 * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUser }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllUser")
	public JAXBElement<FindAllUser> createFindAllUser(FindAllUser value) {
		return new JAXBElement<FindAllUser>(_FindAllUser_QNAME,
				FindAllUser.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link InitTestDb }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "initTestDb")
	public JAXBElement<InitTestDb> createInitTestDb(InitTestDb value) {
		return new JAXBElement<InitTestDb>(_InitTestDb_QNAME, InitTestDb.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllUserResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllUserResponse")
	public JAXBElement<FindAllUserResponse> createFindAllUserResponse(
			FindAllUserResponse value) {
		return new JAXBElement<FindAllUserResponse>(_FindAllUserResponse_QNAME,
				FindAllUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetArticleContentByTitleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "getArticleContentByTitleResponse")
	public JAXBElement<GetArticleContentByTitleResponse> createGetArticleContentByTitleResponse(
			GetArticleContentByTitleResponse value) {
		return new JAXBElement<GetArticleContentByTitleResponse>(
				_GetArticleContentByTitleResponse_QNAME,
				GetArticleContentByTitleResponse.class, null, value);
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
	 * Create an instance of {@link JAXBElement }{@code <}{@link FindAllArticle }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticle")
	public JAXBElement<FindAllArticle> createFindAllArticle(FindAllArticle value) {
		return new JAXBElement<FindAllArticle>(_FindAllArticle_QNAME,
				FindAllArticle.class, null, value);
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
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllArticleResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wanda.flowsoft.com/", name = "findAllArticleResponse")
	public JAXBElement<FindAllArticleResponse> createFindAllArticleResponse(
			FindAllArticleResponse value) {
		return new JAXBElement<FindAllArticleResponse>(
				_FindAllArticleResponse_QNAME, FindAllArticleResponse.class,
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

}
