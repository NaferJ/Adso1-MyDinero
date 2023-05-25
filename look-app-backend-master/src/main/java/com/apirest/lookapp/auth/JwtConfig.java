package com.apirest.lookapp.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEA0aUubMTuIKo7hBnaiCgJ8d+dCcjjq0QD5QXrYMTChXQ/x1oj\r\n" + 
			"rrNyjNT9Iazah8SycN3o9gtvcoPPwEw1v8j8ms4Qwod3EWHzyg2zSholVh9jI/qx\r\n" + 
			"47G3+2lrdhr+6qUYen1wK8kh/XWaQxll4QgBNeNFwbscXRU/ey4M7DaxJG3jumx6\r\n" + 
			"GkqmmdnyaiVZ7Qj0BpbzHp+ZVnzJvSx9TDsNQP2UY44Gy7ixK+3cj2VqUpgqp11u\r\n" + 
			"rh+qGhPG83AFpv/VPdH3EOMfC/+KIKEgf+QZQTwXEyVjZ94Ie7g65T0XGDvXW8mS\r\n" + 
			"B/e7KLy4Tc3OEDTV5fDAEtNKt4GwPaZI7JCziwIDAQABAoIBAQC1aXHF75d8LrL3\r\n" + 
			"ugOPxM3uPqk+mVbhkiaonTm0cC8/mnvCPy8XcvMSCRDOfixQRhZ/cpevqe+TsZop\r\n" + 
			"9gig4AQu1VhNbTnMke2LD9XwrrVWVCzPKpWayePCK9QwzGWAmMKY5EkXqqG+GXEW\r\n" + 
			"1TiCxRF/Fx766LY/mi5nok7ObPlW4iuHyQOu5MFQNS9RMq3/1Mdq1F1Z4z1t5Bj1\r\n" + 
			"jSbAZOlk14PqekF2wBoo9BJkw5oREn63ukhtAV3RGiwT7fAZfosw1TR8KEDd6hvC\r\n" + 
			"s8WWWef6UMZfIlz7ffDgwZJPtE9QYATgAV56zcLabHwh7Ehj5OvfWcIMYrXwXWXj\r\n" + 
			"feLhtQfBAoGBAO7idhlJz9MTn2NBoU/zCP6V9j/FF5oOLl8KzR4qjZ9D1k7cQKNO\r\n" + 
			"CYnKaD3vGKDScCiLILIBt+fR6/ZkjVxAvr6UGcBT6YHZ7pIO3Nuq4yW9HYvouWZl\r\n" + 
			"9/j/fiaDxvhmK8lEDgMtNuIHsLktDGxxSmse6yRs8UWkB51lEs4cKaChAoGBAOCq\r\n" + 
			"a+tLV88w4fQrPOCoFCPmp5/aJQ4trf/UyHxmC5YFa04Pa4+t+mi3zueccw3VDiOI\r\n" + 
			"JIXI95DeRJmymLPLIf8pKQnGpT2YSd3MUIoIatG9kAMHiI7ANIFPX2JLCFBf9Dci\r\n" + 
			"ivhs4z9M/YD3MlK1uDd4AQUVYfPx1g1VswlG4WirAoGBAKbGX+ED0R3x4yLunOou\r\n" + 
			"KKdklsiqlvGs2N7tsYpP9xOrzqC70DyyrKIQN3qVxEHy6O7FQwxDettTiUM5rB8t\r\n" + 
			"7H0s9FV7v9gyCAbcbYDG61Jbl5cn4NgGdHrYFIBbG7wryIH5CzdYh6qB5/U/6B6F\r\n" + 
			"cYkbytphXIhcHhzahahzrPRBAoGBALCu1fhRZFkZBFnVtyamiFBDkQfxsrqXygMs\r\n" + 
			"AA904R+2W1EkZ16zX/8CrsEPFJyINXpUEwTzq15OrnyBK/4Zfj8Ay7lYc5MhNNYt\r\n" + 
			"XBlxzS/teFgpkIdTOPeIyGaww3USQSkrDUeZuo2JqsPFo9nP93QH+CVbT5FdWw1Z\r\n" + 
			"nFdrWtHJAoGAH66j/LaT2d6arMWEiR73NQCDy7w8zhqKJd6MWA1JhGvVXVp30zhv\r\n" + 
			"YhH+uPGbEerWSJb7VgQN2pw4RM/JIH8/x9gEBJbLE2Ls1/0x5Py0AWnzAo+mckx4\r\n" + 
			"ck0qYPySLoUZ2U5JoCBGEDPwo+4/+S069LOZlR9vv8gFXuwh+e+d+5g=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0aUubMTuIKo7hBnaiCgJ\r\n" + 
			"8d+dCcjjq0QD5QXrYMTChXQ/x1ojrrNyjNT9Iazah8SycN3o9gtvcoPPwEw1v8j8\r\n" + 
			"ms4Qwod3EWHzyg2zSholVh9jI/qx47G3+2lrdhr+6qUYen1wK8kh/XWaQxll4QgB\r\n" + 
			"NeNFwbscXRU/ey4M7DaxJG3jumx6GkqmmdnyaiVZ7Qj0BpbzHp+ZVnzJvSx9TDsN\r\n" + 
			"QP2UY44Gy7ixK+3cj2VqUpgqp11urh+qGhPG83AFpv/VPdH3EOMfC/+KIKEgf+QZ\r\n" + 
			"QTwXEyVjZ94Ie7g65T0XGDvXW8mSB/e7KLy4Tc3OEDTV5fDAEtNKt4GwPaZI7JCz\r\n" + 
			"iwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
