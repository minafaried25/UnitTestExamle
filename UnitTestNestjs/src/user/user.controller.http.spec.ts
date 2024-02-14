import { Test, TestingModule } from '@nestjs/testing';
import { UserController } from './user.controller';
import { UserService } from './user.service';
import { UserModel, UserSchema } from './user.model';
import * as request from 'supertest';
import { UserCreateDto } from './user.create.dto';
import { MongooseModule } from '@nestjs/mongoose';
describe('UserControllerHTTP', () => {
  let controller: UserController;
  let userService: UserService;
  let app;
  let module: TestingModule;
  beforeEach(async () => {
    module = await Test.createTestingModule({
      imports: [MongooseModule.forRoot('mongodb+srv://root:root@cluster0.kfunv7z.mongodb.net', {
        dbName: 'Users',
      }),
      MongooseModule.forFeature([{ name: 'users', schema: UserSchema }]),],
      controllers: [UserController],
      providers: [UserService],
    }).compile();
    controller = module.get<UserController>(UserController);
    userService = module.get<UserService>(UserService);
    app = module.createNestApplication();
    await app.init();
  });
  afterEach(async () => {
    await module.close();
  });
  describe('all', () => {
    it('should return an array of users', async () => {
      const result = await controller.all();

      // Check if result is an array
      expect(Array.isArray(result)).toBe(true);

      // Check if each item in the array is of type UserModel
      if (Array.isArray(result)) {
        result.forEach(user => {
          expect(user).toHaveProperty('_id');
          expect(user).toHaveProperty('name');
        });
      }
    });
  });

  describe('create', () => {
    it('should create a new user', async () => {
      const newUser: UserCreateDto = { name: 'NewUser' };

      const result = await controller.create(newUser);
      expect(result).toHaveProperty('_id');
      expect(result).toHaveProperty('name');
    });

    it('should handle validation errors when creating a user', async () => {
      const invalidUser = { name: '' };
      const response = await request(app.getHttpServer())
        .post('/user/create')
        .send(invalidUser);

      expect(response.status).toBe(400);
      // You can also check other properties of the response if needed
      // For example, response.body could contain more detailed error information
    });
  });
});
