package com.example.lonua.seller.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeller is a Querydsl query type for Seller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeller extends EntityPathBase<Seller> {

    private static final long serialVersionUID = 1647078430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeller seller = new QSeller("seller");

    public final ListPath<com.example.lonua.answer.model.entity.Answer, com.example.lonua.answer.model.entity.QAnswer> answerList = this.<com.example.lonua.answer.model.entity.Answer, com.example.lonua.answer.model.entity.QAnswer>createList("answerList", com.example.lonua.answer.model.entity.Answer.class, com.example.lonua.answer.model.entity.QAnswer.class, PathInits.DIRECT2);

    public final StringPath authority = createString("authority");

    public final com.example.lonua.brand.model.entity.QBrand brand;

    public final StringPath createdAt = createString("createdAt");

    public final StringPath sellerEmail = createString("sellerEmail");

    public final NumberPath<Integer> sellerIdx = createNumber("sellerIdx", Integer.class);

    public final StringPath sellerName = createString("sellerName");

    public final StringPath sellerPassword = createString("sellerPassword");

    public final BooleanPath status = createBoolean("status");

    public final StringPath updatedAt = createString("updatedAt");

    public QSeller(String variable) {
        this(Seller.class, forVariable(variable), INITS);
    }

    public QSeller(Path<? extends Seller> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSeller(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSeller(PathMetadata metadata, PathInits inits) {
        this(Seller.class, metadata, inits);
    }

    public QSeller(Class<? extends Seller> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new com.example.lonua.brand.model.entity.QBrand(forProperty("brand")) : null;
    }

}

