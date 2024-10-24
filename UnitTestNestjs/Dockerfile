# Stage 1: Build the application
FROM node:18-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire project into the container
COPY . .

# Build the NestJS app
RUN npm run build

# Stage 2: Run the application
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install only production dependencies
COPY package*.json ./

# Install only production dependencies
RUN npm install --only=production

# Copy the build files from the previous stage
COPY --from=builder /app/dist ./dist

# Expose the port on which the NestJS app will run
EXPOSE 3000

# Command to run the application
CMD ["node", "dist/main"]
