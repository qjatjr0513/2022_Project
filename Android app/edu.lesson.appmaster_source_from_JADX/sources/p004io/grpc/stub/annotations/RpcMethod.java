package p004io.grpc.stub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import p004io.grpc.MethodDescriptor;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* renamed from: io.grpc.stub.annotations.RpcMethod */
public @interface RpcMethod {
    String fullMethodName();

    MethodDescriptor.MethodType methodType();

    Class<?> requestType();

    Class<?> responseType();
}
