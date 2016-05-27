describe('calculator module tests', function() {
    'use strict';

    describe('Adding', function() {
        it('should return 12 when add 5 and 7', function() {
            // given
            var a = 5,
                b = 7,
                result;
            // when
            result = calculate.add(a, b);
            // then
            expect(result).toBe(12);
        });

        it('should throw an error when add and first number is NaN', function() {
            // given
            var a = 'NaN',
                b = 7;
            // when then
            expect(function() {
                calculate.add(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });

        it('should throw an error when add and second number is NaN', function() {
            // given
            var a = 5,
                b = 'NaN';
            // when then
            expect(function() {
                calculate.add(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });
    });

    describe('Substracting', function() {
        it('should return -2 when substract 5 minus 7', function() {
            // given
            var a = 5,
                b = 7,
                result;
            // when
            result = calculate.substract(a, b);
            // then
            expect(result).toBe(-2);
        });

        it('should throw an error when substrack and first number is NaN', function() {
            // given
            var a = 'NaN',
                b = 7;
            // when then
            expect(function() {
                calculate.substract(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });

        it('should throw an error when substrack and second number is NaN', function() {
            // given
            var a = 5,
                b = 'NaN';
            // when then
            expect(function() {
                calculate.substract(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });
    });

    describe('Multiplying', function() {
        it('should return 35 when multiply 5 by 7', function() {
            // given
            var a = 5,
                b = 7,
                result;
            // when
            result = calculate.multiply(a, b);
            // then
            expect(result).toBe(35);
        });

        it('should throw an error when multiply and first number is NaN', function() {
            // given
            var a = 'NaN',
                b = 7;
            // when then
            expect(function() {
                calculate.multiply(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });

        it('should throw an error when multiply and second number is NaN', function() {
            // given
            var a = 5,
                b = 'NaN';
            // when then
            expect(function() {
                calculate.multiply(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });
    });

    describe('Dividing', function() {
        it('should return 0.5 when divide 5 by 10', function() {
            // given
            var a = 5,
                b = 10,
                result;
            // when
            result = calculate.devide(a, b);
            // then
            expect(result).toBe(0.5);
        });

        it('should throw an error when divide and first number is NaN', function() {
            // given
            var a = 'NaN',
                b = 7;
            // when then
            expect(function() {
                calculate.devide(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });

        it('should throw an error when divide and second number is NaN', function() {
            // given
            var a = 5,
                b = 'NaN';
            // when then
            expect(function() {
                calculate.devide(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });

        it('should throw an error when divide by zero', function() {
            // given
            var a = 5,
                b = 0;
            // when then
            expect(function() {
                calculate.devide(a, b);
            }).toThrow(new Error('Numbers not valid'));
        });
    });

    describe('Factorial', function() {
        it('should return 120 for factorial of 5', function() {
            // given
            var a = 5,
                result;
            // when
            result = calculate.factorial(a);
            // then
            expect(result).toBe(120);
        });

        it('should throw an error for factorial of negative number', function() {
            // given
            var a = -2;
            // when then
            expect(function() {
                calculate.factorial(a);
            }).toThrow(new Error('Number not valid'));
        });

        it('should throw an error for factorial of NaN', function() {
            // given
            var a = 'NaN';
            // when then
            expect(function() {
                calculate.factorial(a);
            }).toThrow(new Error('Number not valid'));
        });
    });
});
