package com.vvkee.jutils.bit;

import java.util.List;

public class Main {

	public static class MemberProduct {
		private String productCode;
		private Long status;

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public Long getStatus() {
			return status;
		}

		public void setStatus(Long status) {
			this.status = status;
		}

	}

	public static class Member {
		private List<MemberProduct> memberProducts;
		private Long productStatus;

		public List<MemberProduct> getMemberProducts() {
			return memberProducts;
		}

		public void setMemberProducts(List<MemberProduct> memberProducts) {
			this.memberProducts = memberProducts;
		}

		public Long getProductStatus() {
			return productStatus;
		}

		public void setProductStatus(Long productStatus) {
			this.productStatus = productStatus;
		}

		public Long calcProductStatus() {

			Long productStatus = 0L;

			for (MemberProduct product : memberProducts) {
				try {
					ProductCodeEnum productCodeEnum = ProductCodeEnum.valueOf(product.getProductCode());
					productStatus = FlagUtil.setBitValue(productStatus, productCodeEnum.bitIndex(),
							(product.getStatus() == 1 ? 1 : 0));
				} catch (Exception e) {
					continue;
				}
			}
			return productStatus;
		}
	}

}
