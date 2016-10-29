/**
 * Created by cjl20 on 2016/7/8.
 */
describe('FMCGSystemControllers', function() {

    describe('ProductListCtrl', function(){

        it('should create "products" model with 45 products', function() {
            var scope = {},
                ctrl = new PhoneListCtrl(scope);

            expect(scope.product.length).toBe(45);
        });

        it('should create "phones" model with 3 phones', function() {
            var scope = {},
                ctrl = new PhoneListCtrl(scope);

            expect(scope.product.length).toBe(45);
        });
    });
});
/*
 Chrome: Runner reset.
 .
 Total 1 tests (Passed: 1; Fails: 0; Errors: 0) (2.00 ms)
 Chrome 19.0.1084.36 Mac OS: Run 1 tests (Passed: 1; Fails: 0; Errors 0) (2.00 ms)
 */